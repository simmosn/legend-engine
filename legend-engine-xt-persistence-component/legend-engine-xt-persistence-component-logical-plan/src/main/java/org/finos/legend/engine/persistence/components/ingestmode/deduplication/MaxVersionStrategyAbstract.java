// Copyright 2023 Goldman Sachs
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package org.finos.legend.engine.persistence.components.ingestmode.deduplication;

import org.immutables.value.Value;

import static org.immutables.value.Value.Immutable;
import static org.immutables.value.Value.Style;

@Immutable
@Style(
    typeAbstract = "*Abstract",
    typeImmutable = "*",
    jdkOnly = true,
    optionalAcceptNullable = true,
    strictBuilder = true
)
public interface MaxVersionStrategyAbstract extends VersioningStrategy
{
    @Value.Parameter(order = 0)
    String versioningField();

    @Value.Default
    default VersioningComparator versioningComparator()
    {
        return VersioningComparator.GREATER_THAN;
    }

    @Value.Default
    default boolean performDeduplication()
    {
        return true;
    }

    @Override
    default <T> T accept(VersioningStrategyVisitor<T> visitor)
    {
        return visitor.visitMaxVersionStrategy(this);
    }
}