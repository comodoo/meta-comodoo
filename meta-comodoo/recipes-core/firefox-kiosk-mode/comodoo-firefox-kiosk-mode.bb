require comodoo-firefox-addon-rkiosk.inc

PV = "20161020"
PR = "r4"

PREFS_JS = "prefs.js"
EXTENSIONS_JSON = "extensions.json"
COMODOO-KIOSK_PY = "comodoo-firefox-kiosk.py"
SRC_URI_append = "file://${PREFS_JS} \
                  file://${EXTENSIONS_JSON} \
                  file://${COMODOO-KIOSK_PY} \
                 "

do_unpack_append() {
    cp ${THISDIR}/${PN}/${PREFS_JS} ${WORKDIR}/
    cp ${THISDIR}/${PN}/${EXTENSIONS_JSON} ${WORKDIR}/
    cp ${THISDIR}/${PN}/${COMODOO-KIOSK_PY} ${WORKDIR}/
}

COMODOO_DIR = "/opt/comodoo"
COMODOO_BINDIR = "${COMODOO_DIR}/bin"
COMODOO_DATADIR = "${COMODOO_DIR}/share"
COMODOO_FIREFOX_DATADIR = "${COMODOO_DATADIR}/${FIREFOX_DIRNAME}"

do_install_append() {
    PROFILE_DIR=${D}${COMODOO_FIREFOX_DATADIR}/profile

    install -d ${PROFILE_DIR}
    install -m 0644 ${WORKDIR}/${PREFS_JS} ${PROFILE_DIR}/
    install -m 0644 ${WORKDIR}/${EXTENSIONS_JSON} ${PROFILE_DIR}/
    chown root:root -R ${PROFILE_DIR}

    install -d ${D}${COMODOO_BINDIR}
    install -m 0755 ${WORKDIR}/${COMODOO-KIOSK_PY} ${D}${COMODOO_BINDIR}
    chown root:root ${D}${COMODOO_BINDIR}/${COMODOO-KIOSK_PY}
}

FILES_${PN} += " \
    ${COMODOO_FIREFOX_DATADIR} \
    ${COMODOO_BINDIR}/${COMODOO-KIOSK_PY} \
    "

RDEPENDS_${PN} += " \
    python \
    firefox \
    "
