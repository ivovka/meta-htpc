require ffmpeg.inc

LIC_FILES_CHKSUM = "file://COPYING.GPLv2;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://COPYING.GPLv3;md5=d32239bcb673463ab874e80d47fae504 \
                    file://COPYING.LGPLv2.1;md5=bd7a443320af8c812e4c18d1b79df004 \
                    file://COPYING.LGPLv3;md5=e6a600fd5e1d9cbde2d983680233ad02"

SRC_URI = "http://ffmpeg.org/releases/${BP}.tar.bz2 \
          ${@bb.utils.contains('DISTRO_FEATURES', 'xbmc', '${XBMC_PATCH_SRC_URI}', '', d)} \
          "

XBMC_PATCH_SRC_URI = "file://0001-ffmpeg-xbmc.patch"

SRC_URI[md5sum] = "3d50db9350bfd740f478f95484a528dd"
SRC_URI[sha256sum] = "6cead9b3ccf76b6981335b041f6056135b29dbd0c75298517ab1526fd2c5d209"

