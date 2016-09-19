#!/bin/sh

OWN_COMODOO_DIR="${HOME}/.comodoo"
COMODOO_TEMPLATE_DIR="/usr/share/comodoo"

if [ ! -d  ${OWN_COMODOO_DIR} ]; then
    mkdir -p ${OWN_COMODOO_DIR}

    cp -r ${COMODOO_TEMPLATE_DIR}/* ${OWN_COMODOO_DIR}

    chown ${USER}:${USER} -R ${OWN_COMODOO_DIR}
    chmod -R 0644 ${OWN_COMODOO_DIR}
fi

OWN_FIREFOX_PROFILE_DIR="${OWN_COMODOO_DIR}/firefox-45.3.0/profile/"

firefox --display=:0 --profile ${OWN_FIREFOX_PROFILE_DIR}
