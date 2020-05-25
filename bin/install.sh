#!/bin/bash

CURRENT_DIR=$(cd `dirname $0`; pwd)
PROJECT_DIR=$(cd ${CURRENT_DIR}; cd ../; pwd)

function install_boot() {
    echo "install boot ..."
    dir="${PROJECT_DIR}/lib/boot/"
    cd $dir && mvn clean install
}

function install_dts() {
    echo "install dts ..."
    dir="${PROJECT_DIR}/lib/dts/"
    cd ${dir} && mvn clean install
}

function install_libs() {
    install_boot

    echo "install common ..."
    dir="${PROJECT_DIR}/lib/common/"
    cd ${dir} && mvn clean install

#    echo "install model ..."
#    dir="${PROJECT_DIR}/lib/model/"
#    cd ${dir} && mvn clean install

    echo "install framework ..."
    dir="${PROJECT_DIR}/lib/framework/"
    cd ${dir} && mvn clean install

    echo "install mock ..."
    dir="${PROJECT_DIR}/lib/mock/"
    cd ${dir} && mvn clean install

    install_dts

    echo "install sdk ..."
    dir="${PROJECT_DIR}/lib/sdk/"
    cd ${dir} && mvn clean install

    echo "install wolf-boot-start"
    dir="${PROJECT_DIR}/lib/starter/wolf-boot-starter/"
    cd ${dir} && mvn clean install
}


function install_notice() {
    echo "install middleware.notice"
    dir="${PROJECT_DIR}/middleware/notice/"
    cd ${dir} && mvn clean install
}

function install_middleware {
    install_notice
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
    dir="${PROJECT_DIR}/business/org/"
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

function install_content() {
    echo "install business.content"
    dir="${PROJECT_DIR}/business/content/"
    cd ${dir} && mvn clean install
}

function install_datav() {
    echo "install bigdata.datav"
    dir="${PROJECT_DIR}/bigdata/datav/"
    cd ${dir} && mvn clean install
}

function install_onion() {
    echo "install onion"
    dir="/Users/wingle/code/work/onion/onion-api/"
    cd ${dir} && mvn clean install
}


function install_business() {
    install_account
    install_uc
    install_org
    install_knowledge

    install_goods
    install_trade
    install_ump
    install_pay

    install_content
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
    install_middleware
    install_business
    install_business_starter

    install_datav
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




