SUMMARY = "IPTV plugin for VDR"
HOMEPAGE = "http://www.saunalahti.fi/~rahrenbe/vdr/iptv/"
LICENSE = "GPL-2.0"

LIC_FILES_CHKSUM = "file://COPYING;md5=892f569a555ba9c07a568a7c0c4fa63a"

SRC_URI = "http://www.saunalahti.fi/~rahrenbe/vdr/iptv/files/vdr-iptv-2.2.0.tgz \
    file://0001-vdr-plugin-iptv-gcc6-build.patch \
    "
SRC_URI[md5sum] = "d29cf830d16d65592c06a1adfe351456"
SRC_URI[sha256sum] = "6b0e52a060a26dda5b683ad34a53a0fcf63e5b6de429ef7f18f7c38dd08d647e"

PV = "2.2.0"
PR = "r0"

S = "${WORKDIR}/iptv-${PV}"

DEPENDS = "vdr curl"

do_install () {
  oe_runmake install DESTDIR=${D}
}

PACKAGES_append = " ${PN}-scripts"
INSANE_SKIP_${PN} = "already-stripped"

FILES_${PN}_append = " ${libdir}/vdr/*"
FILES_${PN}-dbg_append = " ${libdir}/vdr/.debug"
FILES_${PN}-scripts = "/usr/share/vdr/plugins/iptv/"
