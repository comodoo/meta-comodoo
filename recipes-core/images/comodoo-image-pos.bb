require comodoo-image-core.bb

IMAGE_INSTALL_append = " \
    firefox \
    firefox-addon-rkiosk \
    comodoo-kiosk-firefox \
    odoo-posboxless \
"
