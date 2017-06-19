SUMMARY = "Fast data collector for Embedded Linux"
HOMEPAGE = "http://fluentbit.io"
BUGTRACKER = "https://github.com/fluent/fluent-bit/issues"

FLUENT-BIT_CONF = "fluent-bit.conf"
SRC_URI = "http://fluentbit.io/releases/0.8/fluent-bit-${PV}.tar.gz \
           file://${FLUENT-BIT_CONF} \
          "
SRC_URI[md5sum] = "ed1b376959524e9008e010a83fe9c341"
SRC_URI[sha256sum] = "a95fd3779bcb8ff09a20d033a99457a03575eac35fa1c3497b78e8eae5262f46"

S = "${WORKDIR}/fluent-bit-${PV}"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2ee41112a44fe7014dce33e26468ba93"

DEPENDS = "zlib"
INSANE_SKIP_${PN}-dev += "dev-elf"

inherit cmake comodoo-install-dir

do_install_append() {
    install -d ${D}${COMODOO_ETCDIR}
    install -m 0644 ${WORKDIR}/${FLUENT-BIT_CONF} ${D}${COMODOO_ETCDIR}
    chown root:root ${D}${COMODOO_ETCDIR}/${FLUENT-BIT_CONF}
}

FILES_${PN} += " \
    ${COMODOO_ETCDIR}/${FLUENT-BIT_CONF} \
    "
