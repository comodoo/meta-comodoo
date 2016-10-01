LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"

PV = "20161001"
PR = "r1"

PREFS_JS = "prefs.js"
EXTENSIONS_JSON = "extensions.json"
COMODOO_SH = "comodoo.sh"
SRC_URI = "file://${PREFS_JS} \
           file://${EXTENSIONS_JSON} \
           file://${COMODOO_SH} \
          "

do_unpack() {
    cp ${THISDIR}/${PN}/${PREFS_JS} ${WORKDIR}/
    cp ${THISDIR}/${PN}/${EXTENSIONS_JSON} ${WORKDIR}/
    cp ${THISDIR}/${PN}/${COMODOO_SH} ${WORKDIR}/
}

FIREFOX_DIRNAME = "firefox-45.3.0"
COMODOO_DATADIR = "${datadir}/comodoo"
COMODOO_FIREFOX_DATADIR = "${COMODOO_DATADIR}/${FIREFOX_DIRNAME}"

do_install() {
    PROFILE_DIR=${D}${COMODOO_FIREFOX_DATADIR}/profile

    install -d ${PROFILE_DIR}
    install -m 0644 ${WORKDIR}/${PREFS_JS} ${PROFILE_DIR}/
    install -m 0644 ${WORKDIR}/${EXTENSIONS_JSON} ${PROFILE_DIR}/
    chown root:root -R ${PROFILE_DIR}

    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/${COMODOO_SH} ${D}${bindir}/
    chown root:root ${D}${bindir}/${COMODOO_SH}
}

FILES_${PN} += "${COMODOO_FIREFOX_DATADIR} \
                ${bindir}/${COMODOO_SH} \
               "
