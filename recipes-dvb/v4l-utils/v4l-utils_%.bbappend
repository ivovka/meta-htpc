FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += " file://rc6_mce"

do_install_append() {
 install -m 0644 ${WORKDIR}/rc6_mce ${D}${sysconfdir}/rc_keymaps/rc6_mce
}
 
