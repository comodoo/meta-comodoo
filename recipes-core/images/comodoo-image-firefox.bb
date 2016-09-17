require recipes-graphics/images/core-image-x11.bb

DECRIPTION = "A very basic X11 image with: \
- Terminal                                 \
- SSH server                               \
- VNC server                               \
- Firefox browser                          \
"

IMAGE_FEATURES_append = " ssh-server-dropbear"
IMAGE_INSTALL_append = " x11vnc firefox"
