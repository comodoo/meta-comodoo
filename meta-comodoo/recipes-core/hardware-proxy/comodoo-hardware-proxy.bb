inherit comodoo-install-dir

require odoo-posboxless_git.inc

PV = "20170619"
PR = "r6"

COMODOO-HW-PROXY_SH = "comodoo-hardware-proxy.sh"
SRC_URI_append = " \
    file://${COMODOO-HW-PROXY_SH} \
    file://0001-Use-a-narrower-filter-to-detect-barcode-scanners.patch \
    "

do_install_append() {
    install -d ${D}${COMODOO_BINDIR}
    install -m 0755 ${WORKDIR}/${COMODOO-HW-PROXY_SH} ${D}${COMODOO_BINDIR}/
    chown root:root ${D}${COMODOO_BINDIR}/${COMODOO-HW-PROXY_SH}
}

FILES_${PN} += " ${COMODOO_BINDIR}/${COMODOO-HW-PROXY_SH}"

RDEPENDS_${PN} += " bash"
