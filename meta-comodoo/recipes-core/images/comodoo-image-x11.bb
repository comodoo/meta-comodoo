SUMMARY = "A very basic X11 image for comodoo project"
LICENSE = "MIT"

inherit core-image distro_features_check

REQUIRED_DISTRO_FEATURES = "x11"

IMAGE_INSTALL_append = " \
    packagegroup-core-x11-xserver \
    matchbox-wm \
    xinit \
    "
