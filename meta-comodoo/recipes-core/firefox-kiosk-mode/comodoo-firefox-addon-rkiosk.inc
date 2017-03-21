SUMMARY = "Real Kiosk is a Firefox extension that defaults to full screen, \
disables all menus, toolbars, key commands and right button menus."
HOMEPAGE = "https://addons.mozilla.org/en-US/firefox/addon/r-kiosk/"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"

DEPENDS = "mozilla-devscripts-native"

RKIOSK_XPI = "r_kiosk-0.9.0-fx.xpi"
SRC_URI = "file://${RKIOSK_XPI}"

do_unpack() {
    rm -fr ${S}
    xpi-unpack ${THISDIR}/${PN}/${RKIOSK_XPI} ${S}
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

FILES_${PN} += " ${FIREFOX_LIBDIR}"
