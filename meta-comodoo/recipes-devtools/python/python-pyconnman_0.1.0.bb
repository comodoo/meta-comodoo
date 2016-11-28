LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"

SRC_URI = "http://pypi.python.org/packages/source/p/pyconnman/pyconnman-${PV}.tar.gz"

SRC_URI[md5sum] = "b7fa82034b1c0e1fb1b518ffe3bb4fc0"
SRC_URI[sha256sum] = "46c64c0692063fd0c9fb0216d49f7884bec9fa9760d8473db4b1e2f8162fab4a"

UPSTREAM_CHECK_URI = "https://pypi.python.org/pypi/pyconnman/"
UPSTREAM_CHECK_REGEX = "/pyconnman/(?P<pver>(\d+[\.\-_]*)+)"

S = "${WORKDIR}/pyconnman-${PV}"

inherit setuptools

RDEPENDS_${PN} = " \
  connman \
  python-dbus \
  python-pygobject \
  "
