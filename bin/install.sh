#!/usr/bin/env bash

function install_framework() {
    cd "${WOLF_FRAMEWORK_DIR}" || exit
    git pull --ff || exit

    local dir="${WOLF_FRAMEWORK_DIR}"
    cd $dir && mvn -T 1C clean install

    return
    #原编译逻辑不执行，暂时不删除
    local modules=(boot principle common framework mock dts starter)
    for module in "${modules[@]}"; do
        echo "install wolf framework: [$module] ..."
        local dir="${WOLF_FRAMEWORK_DIR}/$module/"
        cd $dir && mvn -T 1C clean install
    done
}

function install_app() {
    local target=$2
    local app
    local module

    if [[ $target = *"/"* ]]; then
        app=${target%/*}
        module=${target#*/}
    else
        app=$target
        module=""
    fi

    local app_dir="${wolf_app_dir[$app]}"
    # Compatible with win monolithic app
    if [ -z "$app_dir" ]; then
        app_dir="${wolf_app_deploy_dir[$app]}"
    fi

    cd "${app_dir}/$module" || exit
    mvn -T 1C -DskipTests clean install
}

function main() {
    if [ -z "$1" ] || [ "$1" != "install" ]; then
        echo "please input the right command: win install module-name or win install app-name"
        exit
    fi

    if [ -z "$2" ]; then
        echo "please input module-name or app-name"
        exit
    fi

    case "$2" in
    framework | framework/*)
        install_framework
        ;;
    *)
        local install_function_name="install_$2"
        if type "$install_function_name" &>/dev/null; then
            eval "$install_function_name"
        else
            install_app "$@"
        fi
        ;;
    esac
}

main "$@"
