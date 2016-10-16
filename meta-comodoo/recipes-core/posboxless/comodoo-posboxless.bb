require odoo-posboxless_git.inc

PV = "20161016"
PR = "r2"

COMODOO-POSBOXLESS_SH = "comodoo-posboxless.sh"
SRC_URI_append = " file://${COMODOO-POSBOXLESS_SH}"

COMODOO_DIR = "/opt/comodoo"
COMODOO_BINDIR = "${COMODOO_DIR}/bin"

do_install_append() {
    install -d ${D}${COMODOO_BINDIR}
    install -m 0755 ${WORKDIR}/${COMODOO-POSBOXLESS_SH} ${D}${COMODOO_BINDIR}/
    chown root:root ${D}${COMODOO_BINDIR}/${COMODOO-POSBOXLESS_SH}
}

FILES_${PN} += "${COMODOO_BINDIR}/${COMODOO-POSBOXLESS_SH}"

RDEPENDS_${PN} += "bash"
