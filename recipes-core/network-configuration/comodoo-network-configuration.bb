LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"

PV = "20161009"
PR = "r1"

COMODOO-NET-CONF_PY = "comodoo-network-configuration.py"
SRC_URI = "file://${COMODOO-NET-CONF_PY}"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/${COMODOO-NET-CONF_PY} ${D}${bindir}/
    chown root:root ${D}${bindir}/${COMODOO-NET-CONF_PY}
}

FILES_${PN} += "${bindir}/${COMODOO-NET-CONF_PY}"

RDEPENDS_${PN} += "python"
