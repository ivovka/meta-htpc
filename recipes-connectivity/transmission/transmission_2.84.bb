SUMMARY = "Transmission is a cross-platform BitTorrent client"
HOMEPAGE = "http://www.transmissionbt.com/"
LICENSE = "GPL-2.0"

LIC_FILES_CHKSUM = "file://COPYING;md5=a1923fe8f8ff37c33665716af0ec84f1"
SRC_URI = "https://transmission.cachefly.net/transmission-2.84.tar.xz \
    file://0001-replace-libsystemd-daemon-dependency.patch"

SRC_URI[md5sum] = "411aec1c418c14f6765710d89743ae42"
SRC_URI[sha256sum] = "a9fc1936b4ee414acc732ada04e84339d6755cd0d097bcbd11ba2cfc540db9eb"

inherit autotools systemd useradd
DEPENDS = "zlib openssl curl libevent"
PACKAGECONFIG = "utp daemon ${@base_contains('DISTRO_FEATURES','systemd','systemd','',d)}"
PACKAGECONFIG[utp] = "--enable-utp,--disable-utp"
PACKAGECONFIG[daemon] = "--enable-daemon,--disable-daemon"
PACKAGECONFIG[systemd] = "--with-systemd-daemon,--without-systemd-daemon,systemd"
EXTRA_OECONF = " \
    HAVE_CXX=yes \
    --enable-lightweight \
    --without-gtk \
    --disable-nls \
    --disable-cli \
    "

SYSTEMD_SERVICE_${PN} = "transmission.service"
SYSTEMD_AUTO_ENABLE = "disable"

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "-c 'Transmission BitTorrent Client' -u 169 -g transmission -b '/var/lib' -m -s /bin/false transmission"
GROUPADD_PARAM_${PN} = "-g 169 transmission"
do_install_append () {
    install -D -m644 "${S}/daemon/transmission-daemon.service" "${D}${systemd_unitdir}/system/transmission.service"
}
