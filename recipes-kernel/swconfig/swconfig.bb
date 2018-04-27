DESCRIPTION = "Simple helloworld application"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = "file://Makefile \
           file://cli.c \
           file://swlib.c \
           file://swlib.h \
"

DEPENDS = "libnl virtual/kernel"

S = "${WORKDIR}"

FILES_${PN} = "${bindir}"

EXTRA_OEMAKE += " \
CFLAGS='-I=/usr/include/libnl3 -I. -I${STAGING_KERNEL_DIR}/user_headers/include -std=c99 -D_GNU_SOURCE' \
LDFLAGS='-L=/usr/lib' \
LIBS='-lnl-3 -lnl-cli-3 -lnl-genl-3 -lnl-idiag-3 -lnl-nf-3 -lnl-xfrm-3 -lnl-route-3 -lm' \
"

do_install_append(){
   install -d ${D}/${bindir}
   install -m 755 ${WORKDIR}/swconfig ${D}${bindir}
   install -d ${D}/${libdir}
   install -m 755 ${WORKDIR}/libsw.a ${D}${libdir}
}