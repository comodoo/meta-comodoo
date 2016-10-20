LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"

PV = "20161020"
PR = "r1"

COMODOO-POS_PY = "comodoo-pos.py"
SRC_URI = " file://${COMODOO-POS_PY}"

COMODOO_DIR = "/opt/comodoo"
COMODOO_BINDIR = "${COMODOO_DIR}/bin"

do_install() {
    install -d ${D}${COMODOO_BINDIR}
    install -m 0755 ${WORKDIR}/${COMODOO-POS_PY} ${D}${COMODOO_BINDIR}/
    chown root:root ${D}${COMODOO_BINDIR}/${COMODOO-POS_PY}
}

FILES_${PN} += "${COMODOO_BINDIR}/${COMODOO-POS_PY}"

RDEPENDS_${PN} += "python"
