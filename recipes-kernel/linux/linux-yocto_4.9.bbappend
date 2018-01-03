FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

PR := "${PR}.2"

COMPATIBLE_MACHINE_linkit7688 = "linkit7688"




SRC_URI += "file://linkit7688.scc \
            file://linkit7688.cfg \
            file://linkit7688-standard.scc \
            file://linkit7688-user-config.cfg \
            file://linkit7688-user-features.scc \
            file://linkit7688-user-patches.scc \
            file://defconfig \
           "

# replace these SRCREVs with the real commit ids once you've had
# the appropriate changes committed to the upstream linux-yocto repo
SRCREV_machine_pn-linux-yocto_linkit7688 ?= "${AUTOREV}"
SRCREV_meta_pn-linux-yocto_linkit7688 ?= "${AUTOREV}"

LINUX_VERSION = "4.9.73"
SRCREV="558b65c53f6d6c421d897ad4b651e51ba341fe4dd97122ec922e23ebd2c16e2b"

#Remove the following line once AUTOREV is locked to a certain SRCREV
KERNEL_VERSION_SANITY_SKIP = "1"
