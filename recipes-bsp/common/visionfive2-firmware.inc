VISIONFIVE2FW_DATE ?= "20230320"
# VF2_v3.0.4 - minus the gcc 12.2 updates 
SRC_URI += "git://github.com/starfive-tech/soft_3rdpart.git;protocol=https;lfs=1;branch=JH7110_VisionFive2_devel;rev=d029d9650e584667af013d867f5fa59599e817fa"
HOMEPAGE ?= "https://github.com/starfive-tech/soft_3rdpart"

PV ?= "${VISIONFIVE2FW_DATE}"
