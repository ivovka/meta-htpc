SUMMARY = "Mediacenter software"
DESCRIPTION = "Set of packages formed the mediacenter software"
LICENSE = "MIT"
PR = "r1"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

RDEPENDS_${PN} = "\
    vdr \
    vdr-plugin-dvbapi \
    vdr-plugin-xvdr \
    vdr-plugin-iptv \
    lirc-modules \
    lirc \
    libvdpau \
    xbmc \
    xbmc-addon-xvdr \
    oscam"

