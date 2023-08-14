require linux-mainline-common.inc
FILESEXTRAPATHS =. "${FILE_DIRNAME}/linux-starfive:"
SUMMARY = "VisionFive dev kernel recipe"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
KERNEL_VERSION_SANITY_SKIP = "1"

SRCREV = "${AUTOREV}"

# pin srcrev for now to have a fixed target
# release VF2_v2.11.5
SRCREV:visionfive2 = "d9eee31aaec51ade1641391836c1f07dd2151a4a"
SRCREV:star64 = "765947eacb2408a3a232cbe8093bf28a991f3c35"
SRCREV:pinetabv = "765947eacb2408a3a232cbe8093bf28a991f3c35"

BRANCH = "visionfive"
BRANCH:visionfive2 = "JH7110_VisionFive2_devel"
BRANCH:star64 = "Star64_devel"
BRANCH:pinetabv = "Star64_devel"

FORK ?= "starfive-tech"
FORK:star64 ?= "Fishwaldo"
FORK:pinetabv ?= "Fishwaldo"

REPO ?= "linux"
REPO:star64 ?= "Star64_linux"
REPO:pinetabv ?= "Star64_linux"

SRC_URI = "git://github.com/${FORK}/${REPO}.git;protocol=https;branch=${BRANCH} \
           file://modules.cfg \
           "
SRC_URI:append:beaglev-starlight-jh7100 = " \
           file://extra.cfg \
"
SRC_URI:append:visionfive = " \
           file://extra.cfg \
"

SRC_URI:append:jh7110 = " \
           file://visionfive2-graphics.cfg \
"
SRC_URI:append:pinetabv = " \
           file://0001-add-Panel-Driver.patch \
           file://0002-power-bq25890-add-return-values-to-error-messages.patch \
           file://0003-power-supply-bq25890-Fix-race-causing-oops-at-boot.patch \
           file://0004-power-supply-bq25890-Fix-initial-setting-of-the-F_CO.patch \
           file://0005-power-bq25890-add-POWER_SUPPLY_PROP_TEMP.patch \
           file://0006-power-supply-bq25890-Rename-IILIM-field-to-IINLIM.patch \
           file://0007-power-supply-bq25890-Reduce-reported-CONSTANT_CHARGE.patch \
           file://0008-power-supply-bq25890-Add-a-bq25890_rw_init_data-help.patch \
           file://0009-power-supply-bq25890-Add-support-to-skip-reset-at-pr.patch \
           file://0010-power-supply-bq25890-Add-support-to-read-back-the-se.patch \
           file://0011-power-supply-bq25890-Enable-charging-on-boards-where.patch \
           file://0012-power-supply-bq25890-Drop-dev-platform_data-NULL-che.patch \
           file://0013-power-supply-bq25890-Add-bq25890_set_otg_cfg-helper.patch \
           file://0014-power-supply-bq25890-Add-support-for-registering-the.patch \
           file://0015-power-supply-bq25890-On-the-bq25892-set-the-IINLIM-b.patch \
           file://0016-power-supply-bq25890-Support-higher-charging-voltage.patch \
           file://0017-power-supply-bq25890-Use-the-devm_regmap_field_bulk_.patch \
           file://0018-power-supply-bq25890-Disable-PUMPX_EN-on-errors.patch \
           file://0019-power-supply-bq25890-Add-support-for-setting-IINLIM.patch \
           file://0020-Revert-power-supply-bq25890-Add-support-for-register.patch \
           file://0021-power-supply-bq25890-Add-support-for-registering-the.patch \
           file://0022-power-supply-bq25890-Fix-enum-conversion-in-bq25890_.patch \
           file://0023-power-supply-bq25890-Document-POWER_SUPPLY_PROP_CURR.patch \
           file://0024-power-supply-bq25890-Clean-up-POWER_SUPPLY_PROP_CONS.patch \
           file://0025-power-supply-bq25890-Clean-up-POWER_SUPPLY_PROP_CONS.patch \
           file://0026-power-supply-bq25890-Add-support-for-setting-user-ch.patch \
           file://0027-power-supply-bq25890-Factor-out-regulator-registrati.patch \
           file://0028-power-supply-bq25890-Add-get_voltage-support-to-Vbus.patch \
           file://0029-power-supply-bq25890-Add-Vsys-regulator.patch \
           file://0030-power-supply-bq25890-Only-use-pdata-regulator_init_d.patch \
           file://0031-power-supply-bq25890-Ensure-pump_express_work-is-can.patch \
           file://0032-power-supply-bq25890-Fix-usb-notifier-probe-and-remo.patch \
           file://0033-power-supply-bq25890-Factor-out-chip-state-update.patch \
           file://0034-power-supply-bq25890-Add-HiZ-mode-support.patch \
           file://0035-power-supply-bq25890-Fix-setting-of-F_CONV_RATE-rate.patch \
           file://0036-power-supply-bq25890-Always-take-HiZ-mode-into-accou.patch \
           file://0037-power-supply-bq25890-Support-boards-with-more-then-o.patch \
           file://0038-power-supply-bq25890-Add-support-for-having-a-second.patch \
           file://0039-power-supply-bq25890-Add-new-linux-iinlim-percentage.patch \
           file://0040-power-supply-bq25890_charger-mark-OF-related-data-as.patch \
           file://0041-power-supply-bq25890-Fix-external_power_changed-race.patch \
           file://0042-power-supply-bq25890-Call-power_supply_changed-after.patch \
           file://0043-add-sc89890-support-to-bq25890-driver.patch \
           file://0044-Add-update-gc02m2-and-ov5640-camera-support-and-v4l2.patch \
           file://0045-ASoC-es8316-add-support-for-ESSX8336-ACPI-_HID.patch \
           file://0046-ASoC-es8316-Use-modern-ASoC-DAI-format-terminology.patch \
           file://0047-ASoC-es-Remove-now-redundant-non_legacy_dai_naming-f.patch \
           file://0048-ASoC-codes-Add-support-for-ES8316-producer-mode.patch \
           file://0049-ASoC-codecs-add-suspend-and-resume-for-ES8316.patch \
           file://0050-ASoC-es8316-fix-register-sync-error-in-suspend-resum.patch \
           file://0051-ASoC-es8316-Don-t-use-ranges-based-register-lookup-f.patch \
           file://0052-ASoC-es8316-Increment-max-value-for-ALC-Capture-Targ.patch \
           file://0053-ASoC-es8316-Do-not-set-rate-constraints-for-unsuppor.patch \
           file://0054-ASoc-codecs-ES8316-Fix-DMIC-config.patch \
           file://0055-add-pinetabv-dts-and-defconfig.patch \
           file://pine64_pinetabv_defconfig;subdir=git/arch/riscv/configs \
"


LINUX_VERSION ?= "6.2.0"
LINUX_VERSION:visionfive2 = "5.15.0"
LINUX_VERSION:star64 = "5.15.115"
LINUX_VERSION:pinetabv = "5.15.115"
LINUX_VERSION_EXTENSION:append:beaglev-starlight-jh7100 = "-starlight"

KBUILD_DEFCONFIG:beaglev-starlight-jh7100 = "starfive_jh7100_fedora_defconfig"
KBUILD_DEFCONFIG:visionfive = "visionfive_defconfig"
KBUILD_DEFCONFIG:visionfive2 = "starfive_visionfive2_defconfig"
KBUILD_DEFCONFIG:star64 = "pine64_star64_defconfig"
KBUILD_DEFCONFIG:pinetabv = "pine64_pinetabv_defconfig"

COMPATIBLE_MACHINE = "(beaglev-starlight-jh7100|visionfive|jh7110)"
