#!/bin/sh

PG_DATA=/var/lib/postgresql/data
PG_VERSION_PATH="${PG_DATA}/PG_VERSION"

if [ ! -f ${PG_VERSION_PATH} ]; then
    postgresql-setup initdb

    /etc/init.d/postgresql-server start

    su - postgres -c "createuser --createdb --username postgres --no-createrole --no-superuser ${USER}"
else
    /etc/init.d/postgresql-server start
fi

/opt/odoo/odoo.py --load=web,hw_proxy,hw_posbox_homepage,hw_scale,hw_scanner,hw_escpos
