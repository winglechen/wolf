#!/bin/bash

CURRENT_DIR=$(cd `dirname $0`; pwd)
PROJECT_DIR=$(cd ${CURRENT_DIR}; cd ../; pwd)

function install_boot() {
    echo "install boot ..."
    dir="${PROJECT_DIR}/lib/boot/"
    cd $dir && mvn clean install
}

function install_libs() {
    echo "install common ..."
    dir="${PROJECT_DIR}/lib/common/"
    cd ${dir} && mvn clean install

#    echo "install model ..."
#    dir="${PROJECT_DIR}/lib/model/"
#    cd ${dir} && mvn clean install

    echo "install framework ..."
    dir="${PROJECT_DIR}/lib/framework/"
    cd ${dir} && mvn clean install

    echo "install dts ..."
    dir="${PROJECT_DIR}/lib/dts/"
    cd ${dir} && mvn clean install

    echo "install wolf-boot-start"
    dir="${PROJECT_DIR}/lib/starter/wolf-boot-starter/"
    cd ${dir} && mvn clean install
}

function install_account() {
    echo "install business.account"
    dir="${PROJECT_DIR}/business/account/"
    cd ${dir} && mvn clean install
}

function install_uc() {
    echo "install business.uc"
    dir="${PROJECT_DIR}/business/uc/"
    cd ${dir} && mvn clean install
}

function install_org() {
    echo "install business.org"
    dir="${PROJECT_DIR}/business/organization/"
    cd ${dir} && mvn clean install
}

function install_knowledge() {
    echo "install business.knowledge"
    dir="${PROJECT_DIR}/business/knowledge/"
    cd ${dir} && mvn clean install
}

function install_goods() {
    echo "install business.goods"
    dir="${PROJECT_DIR}/business/goods/"
    cd ${dir} && mvn clean install
}

function install_trade() {
    echo "install business.trade"
    dir="${PROJECT_DIR}/business/trade/"
    cd ${dir} && mvn clean install
}

function install_ump() {
    echo "install business.ump"
    dir="${PROJECT_DIR}/business/ump/"
    cd ${dir} && mvn clean install
}

function install_pay() {
    echo "install business.pay"
    dir="${PROJECT_DIR}/business/pay/"
    cd ${dir} && mvn clean install
}

function install_workflow() {
    echo "install business.worlflow"
    dir="${PROJECT_DIR}/business/workflow/"
    cd ${dir} && mvn clean install
}

function install_onion() {
    echo "install onion"
    dir="/code/work/onion/onion-api/"
    cd ${dir} && mvn clean install
}


function install_business() {
    install_account
    install_uc
    install_org
    install_knowlege

    install_goods
    install_trade
    install_ump
    install_pay

    install_workflow
}

function install_business_starter() {
    echo "install wolf-business-start"
    dir="${PROJECT_DIR}/lib/starter/wolf-business-starter/"
    cd ${dir} && mvn clean install
}

function install_union() {
    install_business_starter

    echo "install app.union"
    dir="${PROJECT_DIR}/app/estar/"
    cd ${dir} && mvn clean install
}

function install_all() {
    install_boot
    install_libs
    install_business
    install_business_starter
    install_union
}

if [ -n "$1" ]; then
  command=$1
else
  command="all"
fi

case "$command" in
all)
  install_all ;;
esac




