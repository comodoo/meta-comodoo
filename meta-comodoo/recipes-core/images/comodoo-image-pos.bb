require comodoo-image-core.bb

IMAGE_FEATURES_append = " ssh-server-dropbear"

IMAGE_INSTALL_append = " \
    comodoo-firefox-kiosk-mode \
    comodoo-hardware-proxy \
    comodoo-pos-launcher \
    comodoo-pos-wizard \
    fluentbit \
    "
