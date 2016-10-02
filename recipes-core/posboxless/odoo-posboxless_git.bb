LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"

PV = "20161001"
PR = "r1"

SRC_URI = "git://github.com/odoo/odoo.git;protocol=git;branch=9.0"
SRCREV = "1a0d173b9af787a4acc515f153e3d5d78f358487"

S = "${WORKDIR}/git"

do_compile() {
    :
}

ODOO_SRCDIR = "${prefix}/src/odoo"

do_install() {
    ADDONS_DIR="addons"
    ODOO_ADDONS_DIR=${D}${ODOO_SRCDIR}/${ADDONS_DIR}

    install -d ${ODOO_ADDONS_DIR}

    cp -R --preserve=mode -v ${ADDONS_DIR}/web ${ODOO_ADDONS_DIR}
    cp -R --preserve=mode -v ${ADDONS_DIR}/web_kanban ${ODOO_ADDONS_DIR}

    cp -R --preserve=mode -v ${ADDONS_DIR}/hw_escpos ${ODOO_ADDONS_DIR}
    cp -R --preserve=mode -v ${ADDONS_DIR}/hw_posbox_homepage ${ODOO_ADDONS_DIR}
    cp -R --preserve=mode -v ${ADDONS_DIR}/hw_proxy ${ODOO_ADDONS_DIR}
    cp -R --preserve=mode -v ${ADDONS_DIR}/hw_scale ${ODOO_ADDONS_DIR}
    cp -R --preserve=mode -v ${ADDONS_DIR}/hw_scanner ${ODOO_ADDONS_DIR}

    POSBOX_DIR=${ADDONS_DIR}/point_of_sale/tools/posbox
    ODOO_POSBOX_DIR=${D}${ODOO_SRCDIR}/${POSBOX_DIR}

    install -d ${ODOO_POSBOX_DIR}

    cp -R --preserve=mode -v ${POSBOX_DIR}/configuration ${ODOO_POSBOX_DIR}

    cp -R --preserve=mode -v openerp ${D}${ODOO_SRCDIR}

    cp --preserve=mode -v odoo.py ${D}${ODOO_SRCDIR}

    chown root:root -R ${D}${ODOO_SRCDIR}
}

FILES_${PN} += "${ODOO_SRCDIR}"
