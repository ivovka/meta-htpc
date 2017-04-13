SUMMARY = "Tvheadend TV streaming server"
HOMEPAGE = "https://tvheadend.org"
DEPENDS = "avahi ffmpeg libtheora libvorbis zlib openssl python-native"
LICENSE = "GPLv3+"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9eef91148a9b14ec7f9df333daebc746"
SRC_URI = "git://github.com/tvheadend/tvheadend.git \
  file://tvheadend.service \
  file://0001-Move-tvheadend-specific-LD-CFLAGS-into-a-helper-vari.patch \
  "
SRCREV = "5cbaac172b4997fbf89667d79ac6e03b46460060"
PV = "4.1"
inherit useradd systemd
S = "${WORKDIR}/git"

do_configure() {
  ./configure \
    --prefix=${prefix} \
    --libdir=${libdir} \
    --bindir=${bindir} \
    --datadir=${datadir} \
    --arch=${TARGET_ARCH} \
    --disable-cccache \
    --disable-satip_server \
    --disable-satip_client \
    --disable-hdhomerun_static \
    --enable-ffmpeg \
    --disable-ffmpeg_static \
    --disable-libtheora_static \
    --disable-libvorbis_static \
    --disable-libfdkaac \
    --disable-libfdkaac_static \
    --disable-libmfx_static \
    --disable-libx264_static \
    --disable-libx265_static \
    --disable-libvpx_static 
}

do_install() {
    oe_runmake install DESTDIR=${D}
    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -d ${D}${systemd_unitdir}/system
        install -m 0644 ${WORKDIR}/tvheadend.service ${D}${systemd_unitdir}/system/tvheadend.service
    fi

}

FILES_${PN} += "${datadir}/${BPN}"
USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "-d /home/tvheadend -U -G video -s /bin/nologin tvheadend"
SYSTEMD_SERVICE_${PN} = "tvheadend.service"
