require recipes-graphics/images/core-image-x11.bb

IMAGE_FEATURES_append = " ssh-server-dropbear"
IMAGE_INSTALL_append = " x11vnc"
