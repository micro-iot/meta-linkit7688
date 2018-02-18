SRC_URI = "git://github.com/openwrt/openwrt.git;protocol=git"

LICENSE="CLOSED"

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git/tools/padjffs2/src"

BBCLASSEXTEND="native nativesdk"

FILES_${PN}_class-native="${D}/${bindir}/*"

do_compile_class-native () {
    ${CC} padjffs2.c ${LDFLAGS} -o padjffs2
}

do_install(){
    install -d ${D}/${bindir}/
    install -m 0755 ${S}/padjffs2 ${D}/${bindir}/ 
}