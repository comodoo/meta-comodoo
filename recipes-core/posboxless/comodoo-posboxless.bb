require odoo-posboxless_git.inc

PV = "20161006"
PR = "r1"

COMODOO-POSBOXLESS_SH = "comodoo-posboxless.sh"
SRC_URI_append = " file://${COMODOO-POSBOXLESS_SH}"

do_install_append() {
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/${COMODOO-POSBOXLESS_SH} ${D}${bindir}/
    chown root:root ${D}${bindir}/${COMODOO-POSBOXLESS_SH}
}

FILES_${PN} += "${bindir}/${COMODOO-POSBOXLESS_SH}"
