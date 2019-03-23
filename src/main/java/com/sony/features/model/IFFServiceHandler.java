package com.sony.features.model;

import com.sony.features.dto.FeatureFlag;
import com.sony.features.dto.FeatureFlags;

public interface IFFServiceHandler {
	FeatureFlags getAllFeatureFlags();
	FeatureFlags setFeatureFlag(FeatureFlag flagToSet);
}
