LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"

SRC_URI = "git://github.com/odoo/odoo.git;protocol=git;branch=10.0"
SRCREV = "5952c394e5e917a6a5f9c0780dd23198c26fbef3"

S = "${WORKDIR}/git"

do_compile() {
    :
}

ODOO_SRCDIR = "/opt/odoo"

do_install() {
    ADDONS_DIR="addons"
    ODOO_ADDONS_DIR=${D}${ODOO_SRCDIR}/${ADDONS_DIR}

    install -d ${ODOO_ADDONS_DIR}

    cp -R --preserve=mode -v ${ADDONS_DIR}/hw_escpos ${ODOO_ADDONS_DIR}
    cp -R --preserve=mode -v ${ADDONS_DIR}/hw_proxy ${ODOO_ADDONS_DIR}
    cp -R --preserve=mode -v ${ADDONS_DIR}/hw_scale ${ODOO_ADDONS_DIR}
    cp -R --preserve=mode -v ${ADDONS_DIR}/hw_scanner ${ODOO_ADDONS_DIR}

    cp -R --preserve=mode -v odoo ${D}${ODOO_SRCDIR}

    cp --preserve=mode -v odoo-bin ${D}${ODOO_SRCDIR}

    chown root:root -R ${D}${ODOO_SRCDIR}
}

FILES_${PN} += " ${ODOO_SRCDIR}"

RDEPENDS_${PN} += " \
    python \
    python-babel \
    python-dateutil \
    python-decorator \
    python-evdev \
    python-imaging \
    python-jinja2 \
    python-lxml \
    python-mako \
    python-passlib \
    python-psutil \
    python-psycopg2 \
    python-pychart \
    python-pyserial \
    python-pytz \
    python-pyusb \
    python-pyyaml \
    python-reportlab \
    python-requests \
    python-werkzeug \
    "
