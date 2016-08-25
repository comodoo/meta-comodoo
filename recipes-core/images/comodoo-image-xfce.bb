DESCRIPTION = "A XFCE minimal image."

IMAGE_INSTALL = "\
    ${ROOTFS_PKGMANAGE_BOOTSTRAP} \
    ${CORE_IMAGE_EXTRA_INSTALL} \
    packagegroup-core-boot \
    packagegroup-core-x11 \
    packagegroup-xfce-base \
    x11vnc \
    firefox \
"

IMAGE_LINGUAS ?= " "

LICENSE = "MIT"

inherit core-image

export IMAGE_BASENAME = "comodoo-image-xfce"

REQUIRED_DISTRO_FEATURES = "x11"

IMAGE_FEATURES += "ssh-server-dropbear"
