SUMMARY = "Group to bring Core Open Source Packages"
LICENSE = "BSD-3-Clause"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PROVIDES = "${PACKAGES}"

PACKAGES = ' \
    packagegroup-qti-core \
    packagegroup-qti-core-recovery \
    packagegroup-qti-core-vm \
'

RDEPENDS:${PN} = " \
    mtd-utils-ubifs \
    sec-config \
"
RDEPENDS:packagegroup-qti-core-vm = " "
