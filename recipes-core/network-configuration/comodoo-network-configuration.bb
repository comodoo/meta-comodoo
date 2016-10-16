LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"

PV = "20161016"
PR = "r2"

COMODOO-NET-CONF_PY = "comodoo-network-configuration.py"
SRC_URI = "file://${COMODOO-NET-CONF_PY}"

COMODOO_DIR = "/opt/comodoo"
COMODOO_BINDIR = "${COMODOO_DIR}/bin"

do_install() {
    install -d ${D}${COMODOO_BINDIR}
    install -m 0755 ${WORKDIR}/${COMODOO-NET-CONF_PY} ${D}${COMODOO_BINDIR}/
    chown root:root ${D}${COMODOO_BINDIR}/${COMODOO-NET-CONF_PY}
}

FILES_${PN} += "${COMODOO_BINDIR}/${COMODOO-NET-CONF_PY}"

RDEPENDS_${PN} += "python"
