SRC_URI = "file://hello.c"
LICENSE="CLOSED"

# Modify these as desired

S = "${WORKDIR}"

FILES_${PN} = "${bindir}/hello"

do_compile () {
    ${CC} hello.c ${LDFLAGS} -o hello
}

do_install(){
    install -d ${D}/${bindir}/
    install -m 0755 ${S}/hello ${D}/${bindir}/ 
}