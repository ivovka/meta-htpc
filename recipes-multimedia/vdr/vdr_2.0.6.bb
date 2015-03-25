SUMMARY = "VDR - The Video Disk Recorder"
HOMEPAGE = "http://www.tvdr.de/"
LICENSE = "GPL-2.0"

LIC_FILES_CHKSUM = "file://COPYING;md5=892f569a555ba9c07a568a7c0c4fa63a"

SRC_URI = "ftp://ftp.tvdr.de/vdr/vdr-${PV}.tar.bz2"
SRC_URI[md5sum] = "f6916524c302f3209fd0af507ab97387"
SRC_URI[sha256sum] = "c33c6431726378d5af575d8cfcacd34a50d17334e091dc4a095b6b75bc99b972"

S = "${WORKDIR}/${PN}-${PV}"

DEPENDS = "fontconfig freetype gettext-native libcap jpeg ncurses"

EXTRA_OEMAKE = "'CONFDIR=/etc/vdr' 'VIDEODIR=${datadir}/vdr/video'"

do_install () {
  oe_runmake install DESTDIR=${D} PREFIX="/usr" CONFDIR="/etc/vdr" VIDEODIR="${datadir}/vdr/video"
}

PACKAGES_DYNAMIC += "^vdr-plugin-.*"

#TODO: Add locale files to corresponding package
python populate_packages_prepend () {
    vdr_libdir = d.expand('${libdir}/vdr')
    do_split_packages(d, vdr_libdir, '^libvdr-(.*)\.so\.${PV}$', 'vdr-plugin-%s', 'VDR Plugin %s', extra_depends='', prepend=True)
}

