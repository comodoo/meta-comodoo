DEPENDS = "mozilla-devscripts-native"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"

PV = "20160918"
PR = "r1"

RKIOSK_XPI = "addon-r-kiosk-latest.xpi"
PREFS_JS = "prefs.js"
EXTENSIONS_JSON = "extensions.json"
COMODOO_SH = "comodoo.sh"
SRC_URI = "https://addons.mozilla.org/firefox/downloads/latest/r-kiosk/${RKIOSK_XPI} \
           file://${PREFS_JS} \
           file://${EXTENSIONS_JSON} \
           file://${COMODOO_SH} \
          "
SRC_URI[md5sum] = "cf780b3847453c3c899ee5109a083d9d"
SRC_URI[sha256sum] = "80c1dae8a4923b90bea891e9654401bfd497260c18b6da045667897e35df57bb"

do_unpack() {
    rm -fr ${S}
    xpi-unpack ${DL_DIR}/${RKIOSK_XPI} ${S}

    cp ${THISDIR}/${PN}/${PREFS_JS} ${WORKDIR}/
    cp ${THISDIR}/${PN}/${EXTENSIONS_JSON} ${WORKDIR}/
    cp ${THISDIR}/${PN}/${COMODOO_SH} ${WORKDIR}/
}

FIREFOX_DIRNAME = "firefox-45.3.0"
FIREFOX_LIBDIR = "${libdir}/${FIREFOX_DIRNAME}"
COMODOO_DATADIR = "${datadir}/comodoo"
COMODOO_FIREFOX_DATADIR = "${COMODOO_DATADIR}/${FIREFOX_DIRNAME}"

do_install() {
    ADDON_ID_LINE=`egrep '<em:id>' -m 1 install.rdf`
    ADDON_ID=`echo ${ADDON_ID_LINE} | sed "s/.*>\(.*\)<.*/\1/"`
    ADDON_DIR=${D}${FIREFOX_LIBDIR}/browser/extensions

    install -d ${ADDON_DIR}
    cp -R --no-dereference --preserve=mode,links -v ${S} ${ADDON_DIR}/${ADDON_ID}
    chown root:root -R ${ADDON_DIR}

    PROFILE_DIR=${D}${COMODOO_FIREFOX_DATADIR}/profile

    install -d ${PROFILE_DIR}
    install -m 0644 ${WORKDIR}/${PREFS_JS} ${PROFILE_DIR}/
    install -m 0644 ${WORKDIR}/${EXTENSIONS_JSON} ${PROFILE_DIR}/
    chown root:root -R ${PROFILE_DIR}

    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/${COMODOO_SH} ${D}${bindir}/
    chown root:root ${D}${bindir}/${COMODOO_SH}
}

FILES_${PN} += "${FIREFOX_LIBDIR} \
                ${COMODOO_FIREFOX_DATADIR} \
                ${bindir}/${COMODOO_SH} \
               "
