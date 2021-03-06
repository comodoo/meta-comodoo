This README file contains information on the contents of the
comodoo layer.

Please see the corresponding sections below for details.


Dependencies
============

This layer depends on:

```
URI: git://git.openembedded.org/bitbake
branch: morty

URI: git://git.openembedded.org/openembedded-core
layers: meta
branch: morty

URI: git://github.com/OSSystems/meta-browser.git
branch: morty

URI: git://git.yoctoproject.org/meta-cloud-services
layers: meta-openstack
branch: morty

URI: git://github.com/facebook/openbmc.git
branch: helium

URI: git://git.openembedded.org/meta-openembedded
layers: meta-gnome, meta-oe, meta-python
branch: morty

URI: git://github.com/sarnold/meta-small-arm-extra.git
branch: morty
```

Table of Contents
=================

1. Adding the comodoo layer to your build
2. Build an image for Rpi3 with VNC support & fixed screen size
2. (a) Build configuration
3. Build an image for Rpi3 & 'Eleduino 7.0 Inch 1024x600 Touch Screen' with VNC support
3. (a) Build configuration
4. HOWTO launch the kiosk
5. now issues during WIFI configuration

(1) Adding the comodoo layer to your build
=========================================

In order to use this layer, you need to make the build system aware of
it.

Assuming the comodoo layer exists at the top-level of your
yocto build tree, you can add it to the build system by adding the
location of the comodoo layer to bblayers.conf, along with any
other layers needed. e.g.:

```
BBLAYERS ?= " \
    /path/to/yocto/meta \
    /path/to/yocto/meta-poky \
    /path/to/yocto/meta-yocto-bsp \
    /path/to/yocto/meta-browser \
    /path/to/yocto/meta-cloud-services/meta-openstack \
    /path/to/yocto/meta-comodoo/meta-comodoo \
    /path/to/yocto/meta-openbmc \
    /path/to/yocto/meta-openembedded/meta-gnome \
    /path/to/yocto/meta-openembedded/meta-oe \
    /path/to/yocto/meta-openembedded/meta-python \
    /path/to/yocto/meta-small-arm-extra \
    "
```


(2) Build an image for Rpi3 with VNC support & fixed screen size
================================================================

Now, let see how to build the comodoo image for a RaspberryPi 3:


```
1. sudo apt-get install gawk wget git-core diffstat \
   unzip texinfo gcc-multilib build-essential \
   chrpath socat cpio python python3 \
   libsdl1.2-dev xterm
   # WARNING: Essentials packages for Ubuntu, check yours
   # in the following link [1]
2. sudo apt-get install autoconf2.13
3. git clone git://git.yoctoproject.org/poky
4. cd poky/
5. git checkout -b morty origin/morty
   git reset --hard b4cf48ecee9e2c0fd4aad96911aecd1b805cfa5a
6. git clone git://git.openembedded.org/meta-openembedded
   cd meta-openembedded/
   git checkout -b morty origin/morty
   git reset --hard 2810af5ad51d48758f462648992cb46106245f0c
   cd ..
7. git clone git://git.yoctoproject.org/meta-cloud-services
   cd meta-cloud-services/
   git checkout -b morty origin/morty
   git reset --hard 6043a4a17dc5ca9080dac7789e38620b7b1aa372
   cd ..
8. git clone git://github.com/sarnold/meta-small-arm-extra.git
   cd meta-small-arm-extra/
   # NOTICE: You might need to checkout branch morty:
   # git checkout -b morty origin/morty
   git reset --hard 656c651b74e66b04a16c39f8463a9ba670516459
   cd ..
9. git clone git://github.com/facebook/openbmc.git meta-openbmc
   cd meta-openbmc/
   git reset --hard fe11ab3ffb115ebb9eb3cb0dc2f53de6dc346220
   cd ..
   # NOTICE: You have to provide the name of the folder
   # where to clone the repo
10. git clone git://git.yoctoproject.org/meta-raspberrypi
    cd meta-raspberrypi/
    git checkout -b morty origin/morty
    git reset --hard 380bf2ff445c2049bdea15f3bb36e8cb36540345
    cd ..
11. git clone git://github.com/OSSystems/meta-browser.git
    cd meta-browser/
    git checkout -b morty origin/morty
    git reset --hard ee0d264347ca89efe73788a11f31504d1d42b716
    cd ..
12. git clone git@github.com:comodoo/meta-comodoo.git
    # Or git clone git://github.com/comodoo/meta-comodoo.git
    cd meta-comodoo/
    git checkout -b morty origin/morty
    cd ..
13. source oe-init-build-env
14. cd conf/
15. nano bblayers.conf
    # Edit var BBLAYERS as follows:
BBLAYERS ?= " \
    /path/to/yocto/meta \
    /path/to/yocto/meta-poky \
    /path/to/yocto/meta-yocto-bsp \
    /path/to/yocto/meta-browser \
    /path/to/yocto/meta-cloud-services/meta-openstack \
    /path/to/yocto/meta-comodoo/meta-comodoo \
    /path/to/yocto/meta-comodoo/meta-rpi-fixed-fb \
    /path/to/yocto/meta-openbmc \
    /path/to/yocto/meta-openembedded/meta-gnome \
    /path/to/yocto/meta-openembedded/meta-oe \
    /path/to/yocto/meta-openembedded/meta-python \
    /path/to/yocto/meta-raspberrypi \
    /path/to/yocto/meta-small-arm-extra \
  "

16. nano local.conf
    # Add following line to the end of the file:
MACHINE ?= "raspberrypi3"

BBMASK_append = " \
    /meta-cloud-services/meta-openstack/recipes-connectivity/ \
    /meta-cloud-services/meta-openstack/recipes-core/ \
    /meta-cloud-services/meta-openstack/recipes-devtools/erlang/ \
    /meta-cloud-services/meta-openstack/recipes-devtools/perl/ \
    /meta-cloud-services/meta-openstack/recipes-devtools/qemu/ \
    /meta-cloud-services/meta-openstack/recipes-devtools/ruby/ \
    /meta-cloud-services/meta-openstack/recipes-extended/ \
    /meta-cloud-services/meta-openstack/recipes-graphics/ \
    /meta-cloud-services/meta-openstack/recipes-httpd/ \
    /meta-cloud-services/meta-openstack/recipes-kernel/ \
    /meta-cloud-services/meta-openstack/recipes-support/ \
    /meta-openbmc/common/recipes-connectivity \
    /meta-openbmc/common/recipes-core \
    /meta-openbmc/common/recipes-kernel \
    /meta-openbmc/common/recipes-lib \
    /meta-openbmc/common/recipes-tpm \
    /meta-openbmc/common/recipes-utils \
    /meta-small-arm-extra/recipes-bsp \
    /meta-small-arm-extra/recipes-core \
    /meta-small-arm-extra/recipes-devtools/opkg \
    /meta-small-arm-extra/recipes-extended \
    /meta-small-arm-extra/recipes-graphics \
    /meta-small-arm-extra/recipes-kernel \
    /meta-small-arm-extra/recipes-qt \
    /meta-small-arm-extra/recipes-support \
"

17. cd ..
18. bitbake comodoo-image-pos
```

(2a) Build configuration
========================

```
BB_VERSION        = "1.32.0"
BUILD_SYS         = "x86_64-linux"
NATIVELSBSTRING   = "universal"
TARGET_SYS        = "arm-poky-linux-gnueabi"
MACHINE           = "raspberrypi3"
DISTRO            = "poky"
DISTRO_VERSION    = "2.2.2"
TUNE_FEATURES     = "arm armv7ve vfp thumb neon vfpv4 callconvention-hard cortexa7"
TARGET_FPU        = "hard"
meta              
meta-poky         
meta-yocto-bsp    = "morty:b4cf48ecee9e2c0fd4aad96911aecd1b805cfa5a"
meta-browser      = "morty:ee0d264347ca89efe73788a11f31504d1d42b716"
meta-openstack    = "morty:6043a4a17dc5ca9080dac7789e38620b7b1aa372"
meta-comodoo      
meta-rpi-fixed-fb = "morty:db191fc373820ac49f88379f62b07f776cc5250a"
meta-openbmc      = "helium:fe11ab3ffb115ebb9eb3cb0dc2f53de6dc346220"
meta-gnome        
meta-oe           
meta-python       = "morty:2810af5ad51d48758f462648992cb46106245f0c"
meta-raspberrypi  = "morty:380bf2ff445c2049bdea15f3bb36e8cb36540345"
meta-small-arm-extra = "morty:656c651b74e66b04a16c39f8463a9ba670516459"
```

(3) Build an image for Rpi3 & 'Eleduino 7.0 Inch 1024x600 Touch Screen' with VNC support
=========================================================================================

To build an image for this hardware:

- RaspberryPi 3
- Eleduino 7.0 Inch 1024x600 Pixel IPS Hdmi Input Capacitive Touch Screen[2]

Follow the same steps described in section II but in step 15,
modify the file bblayers.conf with this content instead:

```
BBLAYERS ?= " \
  /path/to/yocto/meta \
  /path/to/yocto/meta-poky \
  /path/to/yocto/meta-yocto-bsp \
  /path/to/yocto/meta-browser \
  /path/to/yocto/meta-cloud-services/meta-openstack \
  /path/to/yocto/meta-comodoo/meta-comodoo \
  /path/to/yocto/meta-comodoo/meta-rpi-eleduino-7inch-1024x600 \
  /path/to/yocto/meta-openbmc \
  /path/to/yocto/meta-openembedded/meta-gnome \
  /path/to/yocto/meta-openembedded/meta-oe \
  /path/to/yocto/meta-openembedded/meta-python \
  /path/to/yocto/meta-raspberrypi \
  /path/to/yocto/meta-small-arm-extra \
  "
```


(3a) Build configuration
========================

```
BB_VERSION        = "1.32.0"
BUILD_SYS         = "x86_64-linux"
NATIVELSBSTRING   = "universal"
TARGET_SYS        = "arm-poky-linux-gnueabi"
MACHINE           = "raspberrypi3"
DISTRO            = "poky"
DISTRO_VERSION    = "2.2.2"
TUNE_FEATURES     = "arm armv7ve vfp thumb neon vfpv4 callconvention-hard cortexa7"
TARGET_FPU        = "hard"
meta              
meta-poky         
meta-yocto-bsp    = "morty:b4cf48ecee9e2c0fd4aad96911aecd1b805cfa5a"
meta-browser      = "morty:ee0d264347ca89efe73788a11f31504d1d42b716"
meta-openstack    = "morty:6043a4a17dc5ca9080dac7789e38620b7b1aa372"
meta-comodoo      
meta-rpi-eleduino-7inch-1024x600 = "morty:db191fc373820ac49f88379f62b07f776cc5250a"
meta-openbmc      = "helium:fe11ab3ffb115ebb9eb3cb0dc2f53de6dc346220"
meta-gnome        
meta-oe           
meta-python       = "morty:2810af5ad51d48758f462648992cb46106245f0c"
meta-raspberrypi  = "morty:380bf2ff445c2049bdea15f3bb36e8cb36540345"
meta-small-arm-extra = "morty:656c651b74e66b04a16c39f8463a9ba670516459"
```

(4) HOWTO launch the kiosk
==========================

1. Connect an "Eleduino Touch Screen"[1] & a keyboard to the RaspberryPi.
2. Switch it on.
3. Wait until a list of WIFI's appears on screen. If it is empty, press the
   button "Re-scan" to refresh the list (you might have to do this a couple of
   times).
4. Select your WIFI on the list.
5. On the next screen, type the passphrase for the WIFI and press "Connect".
6. If everything is OK, a new screen will appear where you can type the URL
   of your Odoo server. If it was not possible to connect to the WIFI, you will
   be redirected to the list of WIFI's.
7. Type the Odoo server URL and press "Use".
8. Finally, the Odoo login page will be presented.

In case you built an image only with VNC support, repeat the steps
described before but after switching on the Rpi, open an SSH connection
to the device, launch the 'x11vnc' app and then execute a VNC client
in your host computer. From this point on, all the steps are the same.


(5) Know issues during WIFI configuration
=========================================

* WIFI is disconnected or the device lost connection for a short
  period of time:
  It should reconnect on its own but it does not. I am afraid this
  is a WIP so, until we fix this problem, you have to reboot your
  device. On the bright side, it will automatically reconnect after
  the reboot.

[1] http://www.yoctoproject.org/docs/2.2.1/mega-manual/mega-manual.html#required-packages-for-the-host-development-system

[2] http://www.eleduino.com/7-0-Inch-1024x600-Pixel-IPS-Hdmi-Input-Capacitive-Touch-Screen-Support-Raspberry-pi-Banana-Pi-Pro-Be-p10533.html
