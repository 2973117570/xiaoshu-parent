package org.xiaoshu.service.provider.impl;

import org.springframework.stereotype.Service;
import org.xiaoshu.service.model.UserRecords;
import org.xiaoshu.service.provider.UserRecordsProvider;
import org.xiaoshu.common.provider.impl.BaseProviderImpl;

@Service(value="userRecordsProvider")
public class UserRecordsProviderImpl extends BaseProviderImpl<UserRecords> implements UserRecordsProvider{

}
