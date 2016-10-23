require comodoo-image-core.bb

IMAGE_INSTALL_append = " \
    comodoo-pos-launcher \
    \
    comodoo-firefox-kiosk-mode \
    firefox \
    \
    comodoo-server-url-configuration \
    cherryPy \
    \
    comodoo-network-configuration \
    eca-web \
    connman \
    \ 
    comodoo-hardware-proxy \
    postgresql \
    postgresql-client \
    python-pyserial \
    python-pyusb \
    python-lxml \
    python-passlib \
    python-werkzeug \
    python-decorator \
    python-dateutil \
    python-pytz \
    python-psycopg2 \
    python-babel \
    python-pyyaml \
    python-imaging \
    python-reportlab \
    python-mako \
    python-pychart \
    python-psutil \
    python-requests \
    python-jinja2 \
    python-docutils \
    python-evdev \
"
