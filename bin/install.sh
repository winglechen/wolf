#!/bin/bash

CURRENT_DIR=$(cd `dirname $0`; pwd)
PROJECT_DIR=$(cd ${CURRENT_DIR}; cd ../; pwd)
echo "project root path is: $PROJECT_DIR/"

function install_boot() {
    echo "install boot ..."
    dir="${PROJECT_DIR}/lib/boot/"
    cd $dir && mvn clean install
}

function install_libs() {
    echo "install common ..."
    dir="${PROJECT_DIR}/lib/common/"
    cd ${dir} && mvn clean install

    echo "install model ..."
    dir="${PROJECT_DIR}/lib/model/"
    cd ${dir} && mvn clean install

    echo "install framework ..."
    dir="${PROJECT_DIR}/lib/framework/"
    cd ${dir} && mvn clean install

    echo "install wolf-boot-start"
    dir="${PROJECT_DIR}/lib/starter/wolf-boot-starter/"
    cd ${dir} && mvn clean install
}

function install_business() {
    echo "install business.account"
    dir="${PROJECT_DIR}/business/account/"
    cd ${dir} && mvn clean install

    echo "install business.uc"
    dir="${PROJECT_DIR}/business/uc/"
    cd ${dir} && mvn clean install
    
    echo "install business.goods"
    dir="${PROJECT_DIR}/business/goods/"
    cd ${dir} && mvn clean install

    echo "install business.trade"
    dir="${PROJECT_DIR}/business/trade/"
    cd ${dir} && mvn clean install

}

function install_business_starter() {
    echo "install wolf-business-start"
    dir="${PROJECT_DIR}/lib/starter/wolf-business-starter/"
    cd ${dir} && mvn clean install
}

function install_union() {
    echo "install business.union"
    dir="${PROJECT_DIR}/business/union/"
    cd ${dir} && mvn clean install
}


install_boot
install_libs
install_business
install_business_starter
install_union

