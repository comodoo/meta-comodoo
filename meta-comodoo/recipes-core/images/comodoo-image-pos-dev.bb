require comodoo-image-pos.bb

IMAGE_INSTALL_append = " \
    python-pip \
    \
    openssh-sftp-server \
    \
    connman-client \
    "
