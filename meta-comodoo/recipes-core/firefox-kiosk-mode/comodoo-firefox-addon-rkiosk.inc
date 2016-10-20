LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"

DEPENDS = "mozilla-devscripts-native"

RKIOSK_XPI = "addon-r-kiosk-latest.xpi"
SRC_URI = "https://addons.mozilla.org/firefox/downloads/latest/r-kiosk/${RKIOSK_XPI}"
SRC_URI[md5sum] = "cf780b3847453c3c899ee5109a083d9d"
SRC_URI[sha256sum] = "80c1dae8a4923b90bea891e9654401bfd497260c18b6da045667897e35df57bb"

do_unpack() {
    rm -fr ${S}
    xpi-unpack ${DL_DIR}/${RKIOSK_XPI} ${S}
}

FIREFOX_DIRNAME = "firefox-45.3.0"
FIREFOX_LIBDIR = "${libdir}/${FIREFOX_DIRNAME}"

do_install() {
    ADDON_ID_LINE=`egrep '<em:id>' -m 1 install.rdf`
    ADDON_ID=`echo ${ADDON_ID_LINE} | sed "s/.*>\(.*\)<.*/\1/"`
    ADDON_DIR=${D}${FIREFOX_LIBDIR}/browser/extensions

    install -d ${ADDON_DIR}
    cp -R --no-dereference --preserve=mode,links -v ${S} ${ADDON_DIR}/${ADDON_ID}
    chown root:root -R ${ADDON_DIR}
}

FILES_${PN} += "${FIREFOX_LIBDIR}"
