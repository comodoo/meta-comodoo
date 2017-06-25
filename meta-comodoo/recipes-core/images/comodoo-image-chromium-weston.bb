require recipes-graphics/images/core-image-weston.bb

IMAGE_INSTALL_append = " \
    chromium-wayland \
    "
