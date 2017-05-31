LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"

PV = "20170532"
PR = "r4"

COMODOO-POS_PY = "comodoo-pos.py"
COMODOO-PROFILE = "profile"
COMODOO-XINITRC = "xinitrc"
SRC_URI = "file://${COMODOO-POS_PY} \
           file://${COMODOO-PROFILE} \
           file://${COMODOO-XINITRC} \
          "

COMODOO_DIR = "/opt/comodoo"
COMODOO_BINDIR = "${COMODOO_DIR}/bin"

COMODOO_PROFILE_DIR = "${sysconfdir}"
COMODOO_XINITRC_DIR = "${sysconfdir}/X11/xinit"

do_install() {
    install -d ${D}${COMODOO_BINDIR}
    install -m 0755 ${WORKDIR}/${COMODOO-POS_PY} ${D}${COMODOO_BINDIR}/
    chown root:root ${D}${COMODOO_BINDIR}/${COMODOO-POS_PY}

    install -d ${D}${COMODOO_PROFILE_DIR}
    install -m 0644 ${WORKDIR}/${COMODOO-PROFILE} ${D}${COMODOO_PROFILE_DIR}/
    chown root:root ${D}${COMODOO_PROFILE_DIR}/${COMODOO-PROFILE}

    install -d ${D}${COMODOO_XINITRC_DIR}
    install -m 0644 ${WORKDIR}/${COMODOO-XINITRC} ${D}${COMODOO_XINITRC_DIR}/
    chown root:root ${D}${COMODOO_XINITRC_DIR}/${COMODOO-XINITRC}
}

FILES_${PN} += " \
    ${COMODOO_BINDIR}/${COMODOO-POS_PY} \
    ${COMODOO_PROFILE_DIR}/${COMODOO-PROFILE} \
    ${COMODOO_XINITRC_DIR}/${COMODOO-XINITRC} \
    "

RDEPENDS_${PN} += " \
  busybox \
  python \
  python-argparse \
  xinit \
  "
