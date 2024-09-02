SUMMARY = "Group to bring Core Open Source Packages"
LICENSE = "BSD-3-Clause"

PACKAGE_ARCH = "${MACHINE_ARCH}"
inherit packagegroup

PROVIDES = "${PACKAGES}"

PCIUTILS ?= 'False'
PCIUTILS:sa525m = 'True'
PCIUTILS:sa525m-emmc = 'True'

PACKAGES = ' \
    packagegroup-qti-core \
    packagegroup-qti-core-recovery \
    packagegroup-qti-core-vm \
'

RDEPENDS:${PN} = " \
    mtd-utils-ubifs \
    sec-config \
    ${@oe.utils.conditional('PCIUTILS', 'True', 'pciutils', '', d)} \
"
RDEPENDS:packagegroup-qti-core-vm = " "
