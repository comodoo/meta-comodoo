LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"

PV = "20161021"
PR = "r2"

COMODOO-POS_PY = "comodoo-pos.py"
COMODOO-POS_SH = "70xcomodoo-pos.sh"
SRC_URI = "file://${COMODOO-POS_PY} \
           file://${COMODOO-POS_SH} \
          "

COMODOO_DIR = "/opt/comodoo"
COMODOO_BINDIR = "${COMODOO_DIR}/bin"

XSESSION_DIR = "${sysconfdir}/X11/Xsession.d"

do_install() {
    install -d ${D}${COMODOO_BINDIR}
    install -m 0755 ${WORKDIR}/${COMODOO-POS_PY} ${D}${COMODOO_BINDIR}/
    chown root:root ${D}${COMODOO_BINDIR}/${COMODOO-POS_PY}

    install -d ${D}${XSESSION_DIR}
    install -m 0755 ${WORKDIR}/${COMODOO-POS_SH} ${D}${XSESSION_DIR}/
    chown root:root ${D}${XSESSION_DIR}/${COMODOO-POS_SH}
}

FILES_${PN} += "${COMODOO_BINDIR}/${COMODOO-POS_PY} \
                ${XSESSION_DIR}/${COMODOO-POS_SH} \
               "

RDEPENDS_${PN} += "bash python"
