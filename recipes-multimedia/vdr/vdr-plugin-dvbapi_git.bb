SUMMARY = "DVBAPI plugin for VDR"
HOMEPAGE = "https://github.com/manio/vdr-plugin-dvbapi"
LICENSE = "GPL-2.0"

LIC_FILES_CHKSUM = "file://COPYING;md5=892f569a555ba9c07a568a7c0c4fa63a"

SRC_URI = "git://github.com/manio/vdr-plugin-dvbapi.git;protocol=http"
SRC_URI[md5sum] = "f6916524c302f3209fd0af507ab97387"
SRC_URI[sha256sum] = "c33c6431726378d5af575d8cfcacd34a50d17334e091dc4a095b6b75bc99b972"
SRCREV = "490d5a2ed58f090442dffd4d95e5d201b545c153"
PV = "1.0.6+gitr${SRCPV}"
PR = "r0"

S = "${WORKDIR}/git"

DEPENDS = "vdr"

do_install () {
  oe_runmake install DESTDIR=${D}
}

FILES_${PN}_append = " ${libdir}/vdr/*"
FILES_${PN}-dbg_append = " ${libdir}/vdr/.debug"
