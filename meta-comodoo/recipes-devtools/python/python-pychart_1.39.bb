LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"

inherit distutils

S = "${WORKDIR}/PyChart-${PV}"

SRC_URI = "http://download.gna.org/pychart/PyChart-${PV}.tar.gz"
SRC_URI[md5sum] = "f1f509a1c4623056c8e780bb7c9a05c5"
SRC_URI[sha256sum] = "882650928776a7ca72e67054a9e0ac98f78645f279c0cfb5910db28f03f07c2e"
