SUMMARY = "Mediacenter software"
DESCRIPTION = "Set of packages formed the mediacenter software"
LICENSE = "MIT"
PR = "r1"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

RDEPENDS_${PN} = "\
    vdr \
    vdr-plugin-dvbapi \
    vdr-plugin-vnsiserver \
    vdr-plugin-iptv \
    v4l-utils \
    libvdpau \
    kodi \
    xbmc-addon-xvdr \
    oscam"

