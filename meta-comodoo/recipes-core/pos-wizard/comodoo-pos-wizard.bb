LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"

PV = "20161204"
PR = "r1"

SRC_URI = "git://github.com/comodoo/comodoo-pos-wizard.git;protocol=git"
SRCREV = "3c645a6bf845d7bffd1886169dcb946e579f3ec9"

S = "${WORKDIR}/git"

do_compile() {
    :
}

COMODOO_DIR = "/opt/comodoo"
COMODOO_LIBDIR = "${COMODOO_DIR}/lib"
COMODOO_POSWIZARDDIR = "${COMODOO_LIBDIR}/pos-wizard"

do_install() {
    install -d ${D}${COMODOO_POSWIZARDDIR}

    cp --preserve=mode -v *.py ${D}${COMODOO_POSWIZARDDIR}

    chown root:root -R ${D}${COMODOO_POSWIZARDDIR}
}

FILES_${PN} += " ${COMODOO_POSWIZARDDIR}"

RDEPENDS_${PN} += " \
    python \
    cherryPy \
    python-pyconnman \
    python-dbus \
    python-pygobject \
    "
