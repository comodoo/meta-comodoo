require comodoo-image-pos.bb

IMAGE_FEATURES_append = " ssh-server-dropbear"

IMAGE_INSTALL_append = " \
    connman-client \
    htop \
    openssh-sftp-server \
    python-pip \
    tree \
    x11vnc \
    "
