LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"

PV = "20161023"
PR = "r1"

COMODOO-SRV-URL-CONF_PY = "comodoo-server-url-configuration.py"
COMODOO-SRV-URL-WEB_PY = "comodoo-server-url-web.py"
SRC_URI = " \
    file://${COMODOO-SRV-URL-CONF_PY} \
    file://${COMODOO-SRV-URL-WEB_PY} \
    "

COMODOO_DIR = "/opt/comodoo"
COMODOO_BINDIR = "${COMODOO_DIR}/bin"

do_install() {
    install -d ${D}${COMODOO_BINDIR}

    install -m 0755 ${WORKDIR}/${COMODOO-SRV-URL-CONF_PY} ${D}${COMODOO_BINDIR}/
    chown root:root ${D}${COMODOO_BINDIR}/${COMODOO-SRV-URL-CONF_PY}

    install -m 0755 ${WORKDIR}/${COMODOO-SRV-URL-WEB_PY} ${D}${COMODOO_BINDIR}/
    chown root:root ${D}${COMODOO_BINDIR}/${COMODOO-SRV-URL-WEB_PY}
}

FILES_${PN} += " \
    ${COMODOO_BINDIR}/${COMODOO-SRV-URL-CONF_PY} \
    ${COMODOO_BINDIR}/${COMODOO-SRV-URL-WEB_PY} \
    "

RDEPENDS_${PN} += "python"
